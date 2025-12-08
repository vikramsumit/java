def count_substring(string, sub_string):
    reversed_s = string[::-1]
    print(reversed_s)
    return reversed_s.count(sub_string)

if __name__ == '__main__':
    string = input().strip()
    sub_string = input().strip()
    
    count = count_substring(string, sub_string)
    print(count)