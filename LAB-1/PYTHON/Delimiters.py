
def delimiter(s):

    temp = []
    keys = [";", ")", "(", "}", "{", "]", "[", "'",'"',"//","/*","*/"]

    for i in keys :
        if s.find(i) != -1:
            temp.append(i)

    return temp
