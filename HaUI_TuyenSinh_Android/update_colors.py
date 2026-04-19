import os

layout_dir = r'd:\Mobile_Kotlin_DevPro\DACK\HaUI_TuyenSinh_Android\app\src\main\res\layout'

replacements = {
    '\"#F5F5F5\"': '\"@color/bg_page\"',
    '\"#FFFFFF\"': '\"@color/bg_card\"',
    '\"#FFFFFFFF\"': '\"@color/bg_card\"',
    '\"@android:color/white\"': '\"@color/bg_card\"',
    '\"#212121\"': '\"@color/text_primary\"',
    '\"#616161\"': '\"@color/text_secondary\"',
    '\"#757575\"': '\"@color/text_secondary\"'
}

for root, dirs, files in os.walk(layout_dir):
    for file in files:
        if file.endswith('.xml'):
            path = os.path.join(root, file)
            with open(path, 'r', encoding='utf-8') as f:
                content = f.read()
            
            new_content = content
            for old, new in replacements.items():
                new_content = new_content.replace(old, new)
                
            if new_content != content:
                print('Updated ' + file)
                with open(path, 'w', encoding='utf-8') as f:
                    f.write(new_content)
