from google import genai
import os
from dotenv import load_dotenv

load_dotenv("HaUI_AI_Service\.env")

client = genai.Client(api_key=os.getenv("GEMINI_API_KEY"))
for model in client.models.list_models():
    print(model.name)
