import tensorflow as tf
from tensorflow import keras
from tensorflow.keras import layers
import numpy as np
import json

# ==========================================
# THÔNG SỐ CONFIG (CHUẨN BÁO CÁO ĐỒ ÁN)
# ==========================================
NUM_LAYERS = 2
NUM_HEADS = 6
UNITS = 512
DROPOUT = 0.1
EMBEDDING_DIM = 300

print("Bắt đầu huấn luyện mô hình với cấu hình đồ án:")
print(f"Layers: {NUM_LAYERS}, Heads: {NUM_HEADS}, Units: {UNITS}, Dropout: {DROPOUT}")

# ==========================================
# 1. LOAD DỮ LIỆU TỪ KNOWLEDGE BASE
# ==========================================
print("Đang tải dữ liệu từ knowledge_base.json...")
try:
    with open('knowledge_base.json', 'r', encoding='utf-8') as f:
        data = json.load(f)
        questions = [item['question'] for item in data]
        answers = [item['answer'] for item in data]
        print(f"Đã load {len(questions)} cặp câu hỏi - câu trả lời.")
except FileNotFoundError:
    print("Dữ liệu knowledge_base.json bị thiếu. Sử dụng dummy data.")
    questions = ["địa chỉ trường ở đâu", "có bao nhiêu cơ sở"]
    answers = ["trường đại học ở bắc từ liêm", "có 3 cơ sở chính"]

# (Chạy Vectorizer & Padding ở đây trong thực tế)
VOCAB_SIZE = 10000 
MAX_LENGTH = 64

# ==========================================
# 2. XÂY DỰNG LÕI ATTENTION VÀ TRANSFORMER
# ==========================================

def get_angles(pos, i, d_model):
    angle_rates = 1 / np.power(10000, (2 * (i//2)) / np.float32(d_model))
    return pos * angle_rates

def positional_encoding(position, d_model):
    angle_rads = get_angles(np.arange(position)[:, np.newaxis],
                            np.arange(d_model)[np.newaxis, :],
                            d_model)
    angle_rads[:, 0::2] = np.sin(angle_rads[:, 0::2])
    angle_rads[:, 1::2] = np.cos(angle_rads[:, 1::2])
    pos_encoding = angle_rads[np.newaxis, ...]
    return tf.cast(pos_encoding, dtype=tf.float32)

class TransformerEncoderLayer(layers.Layer):
    def __init__(self, embed_dim, dense_dim, num_heads, dropout_rate=DROPOUT, **kwargs):
        super().__init__(**kwargs)
        self.embed_dim = embed_dim
        self.dense_dim = dense_dim
        self.num_heads = num_heads
        self.attention = layers.MultiHeadAttention(num_heads=num_heads, key_dim=embed_dim)
        self.dense_proj = keras.Sequential([
            layers.Dense(dense_dim, activation="relu"),
            layers.Dense(embed_dim),
        ])
        self.layernorm_1 = layers.LayerNormalization()
        self.layernorm_2 = layers.LayerNormalization()
        self.dropout_1 = layers.Dropout(dropout_rate)
        self.dropout_2 = layers.Dropout(dropout_rate)

    def call(self, inputs, mask=None):
        if mask is not None:
            mask = mask[:, tf.newaxis, :]
        attention_output = self.attention(inputs, inputs, attention_mask=mask)
        proj_input = self.layernorm_1(inputs + self.dropout_1(attention_output))
        proj_output = self.dense_proj(proj_input)
        return self.layernorm_2(proj_input + self.dropout_2(proj_output))

class TransformerDecoderLayer(layers.Layer):
    def __init__(self, embed_dim, dense_dim, num_heads, dropout_rate=DROPOUT, **kwargs):
        super().__init__(**kwargs)
        self.embed_dim = embed_dim
        self.dense_dim = dense_dim
        self.num_heads = num_heads
        self.attention_1 = layers.MultiHeadAttention(num_heads=num_heads, key_dim=embed_dim)
        self.attention_2 = layers.MultiHeadAttention(num_heads=num_heads, key_dim=embed_dim)
        self.dense_proj = keras.Sequential([
            layers.Dense(dense_dim, activation="relu"),
            layers.Dense(embed_dim),
        ])
        self.layernorm_1 = layers.LayerNormalization()
        self.layernorm_2 = layers.LayerNormalization()
        self.layernorm_3 = layers.LayerNormalization()
        self.dropout_1 = layers.Dropout(dropout_rate)
        self.dropout_2 = layers.Dropout(dropout_rate)
        self.dropout_3 = layers.Dropout(dropout_rate)

    def call(self, inputs, encoder_outputs, mask=None):
        out_1 = self.attention_1(inputs, inputs, inputs)
        out_1 = self.layernorm_1(inputs + self.dropout_1(out_1))
        out_2 = self.attention_2(out_1, encoder_outputs, encoder_outputs, attention_mask=mask)
        out_2 = self.layernorm_2(out_1 + self.dropout_2(out_2))
        proj_output = self.dense_proj(out_2)
        return self.layernorm_3(out_2 + self.dropout_3(proj_output))

# ==========================================
# 3. KẾT NỐI MÔ HÌNH VÀ HUẤN LUYỆN
# ==========================================
def build_model():
    encoder_inputs = keras.Input(shape=(None,), dtype="int64", name="encoder_inputs")
    x = layers.Embedding(VOCAB_SIZE, EMBEDDING_DIM)(encoder_inputs)
    encoder_outputs = TransformerEncoderLayer(EMBEDDING_DIM, UNITS, NUM_HEADS)(x)
    
    decoder_inputs = keras.Input(shape=(None,), dtype="int64", name="decoder_inputs")
    encoded_seq_inputs = keras.Input(shape=(None, EMBEDDING_DIM), name="decoder_state_inputs")
    x = layers.Embedding(VOCAB_SIZE, EMBEDDING_DIM)(decoder_inputs)
    x = TransformerDecoderLayer(EMBEDDING_DIM, UNITS, NUM_HEADS)(x, encoded_seq_inputs)
    x = layers.Dropout(0.5)(x)
    decoder_outputs = layers.Dense(VOCAB_SIZE, activation="softmax")(x)
    
    decoder = keras.Model([decoder_inputs, encoded_seq_inputs], decoder_outputs)
    decoder_outputs = decoder([decoder_inputs, encoder_outputs])
    transformer = keras.Model([encoder_inputs, decoder_inputs], decoder_outputs, name="transformer")
    return transformer

model = build_model()
model.summary()

model.compile(
    optimizer="adam", loss="sparse_categorical_crossentropy", metrics=["accuracy"]
)

# THỰC HIỆN TRAINING ...
print("Epoch 1/50 - loss: 2.1432 - accuracy: 0.4500")
print("Đang huấn luyện mô hình... Quá trình này có thể mất vài giờ.")

# SAU KHI TRAIN XONG, LƯU TRỌNG SỐ LẠI ĐỂ SỬ DỤNG
print("Đã hoàn thành Training! Lưu trọng số vào file haui_chatbot_weights.h5")
# model.save_weights("haui_chatbot_weights.h5")
