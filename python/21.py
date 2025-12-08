if __name__ == '__main__':
    s = input()
    
    # print(any(s.isalnum() for c in s))
    # print(any(s.isalpha() for c in s))
    # print(any(s.isdigit() for c in s))
    # print(any(s.islower() for c in s))
    # print(any(s.isupper() for c in s))

    # if __name__ == '__main__':
    # s = input()
    
    print(any(c.isalnum() for c in s))
    print(any(c.isalpha() for c in s))
    print(any(c.isdigit() for c in s))
    print(any(c.islower() for c in s))
    print(any(c.isupper() for c in s))
