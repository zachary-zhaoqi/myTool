# str='请输入'
# str='\"请输入\"'
str='\'请输入\''

newStr=str.split("\"")
if len(newStr)==1:
  newStr=str.split("\'")

if len(newStr)==1:
  newStr=newStr[0]
else:
  newStr=newStr[1]

print(newStr)