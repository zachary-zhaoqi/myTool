import pyperclip
from pynput import keyboard
import urllib.request
import urllib.parse
import json
import time

chara=False
charb=False
keyboardController = keyboard.Controller()
Key=keyboard.Key
print("模块名\n")
moduleName=input()

# 如果有 ""与'' 去除
def extract(str):
    newStr=str.split("\"")
    if len(newStr)==1:
        newStr=str.split("\'")

    if len(newStr)==1:
        newStr=newStr[0]
    else:
        newStr=newStr[1]
    return newStr;



# 将中文翻译为英文translateChineseIntoEnglish
def translate(str):
    url = 'http://fanyi.youdao.com/translate?smartresult=dict&smartresult=rule'

    data = {}
    data['i'] = str
    data['to'] = 'AUTO'
    data['smartresult'] = 'dict'
    data['client'] = 'fanyideskweb'
    data['salt'] = '1517200217152'
    data['sign'] = 'fc8a26607798294e102f7b4e60cc2686'
    data['doctype'] = 'json'
    data['version'] = '2.1'
    data['keyfrom'] = 'fanyi.web'
    data['action'] = 'FY_BY_CLICKBUTTION'
    data['typoResult'] = 'true'
    data = urllib.parse.urlencode(data).encode('utf-8')

    #方法二：用add_header（）方法添加headlAal参数
    req = urllib.request.Request(url,data)
    req.add_header('User-Agent','Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/63.0.3239.132 Safari/537.36')
    response = urllib.request.urlopen(req)
    html = response.read().decode('utf-8')
 
    target = json.loads(html)
    return target['translateResult'][0][0]['tgt']

# 将其转化为驼峰Transformed into hump
def transformedIntoHump(str):
    newStr=str.title().split()
    newStr[0]=newStr[0].lower()
    newStr=''.join(newStr)
    return newStr

# 进行处理
def hanle(char):
    global moduleName
    oldclipStr=pyperclip.paste()
    oldclipStr=extract(oldclipStr)
    newStr=translate(oldclipStr)# 翻译
    newStr=transformedIntoHump(newStr)# 转驼峰

    # window.intlFormat("jlt.mon.label.hello", "你好")
    if char=='m':
        newStr='window.intlFormat("jlt.'+moduleName+'.menu.'+newStr+'", "'+oldclipStr+'")'
    elif char=='b':
        newStr='window.intlFormat("jlt.'+moduleName+'.button.'+newStr+'", "'+oldclipStr+'")'
    elif char=='l':
        newStr='window.intlFormat("jlt.'+moduleName+'.label.'+newStr+'", "'+oldclipStr+'")'
    elif char=='g':
        newStr='window.intlFormat("jlt.'+moduleName+'.grid.'+newStr+'", "'+oldclipStr+'")'
    elif char=='d':
        newStr='window.intlFormat("jlt.'+moduleName+'.dictionary.'+newStr+'", "'+oldclipStr+'")'
    elif char=='i':
        newStr='window.intlFormat("jlt.'+moduleName+'.impexp.'+newStr+'", "'+oldclipStr+'")'
    elif char=='p':
        newStr='window.intlFormat("jlt.'+moduleName+'.prompt.'+newStr+'", "'+oldclipStr+'")'
    else:
        return 

    pyperclip.copy(newStr)
    print("============="+translate(oldclipStr))
    return 

def on_press(key):
    global chara,charb,keyboardController,Key

    try:
        if hasattr(key, 'name'):
            if key.name=='caps_lock':
                charb=chara;
                chara=True;
            else:
                charb=chara=False                
        else:
            if chara&charb:
                # Press and release space
                keyboardController.press(Key.backspace)
                keyboardController.release(Key.backspace)
                hanle(key.char)
            charb=chara=False
        print('key {0} pressed'.format(key))
    except AttributeError:
        print('special key {0} pressed'.format(key))

def on_release(key):
    print('{0} released'.format(key))
    if key == keyboard.Key.esc:
        # Stop listenerL
        return False

# Collect events until released
with keyboard.Listener(on_press=on_press,on_release=on_release) as listener:
    listener.join()

# # ...or, in a non-blocking fashion:
# listener = mouse.Listener(
#     on_press=on_press,
#     on_release=on_release)
# listener.start()