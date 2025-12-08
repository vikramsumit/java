if __name__ == '__main__':
    N = int(input())

    raju = []

    raju.insert(0 , 5)
    raju.insert(1, 10)
    raju.insert(0, 6)
    print(raju)
    raju.remove(6)
    raju.append(9)
    raju.append(1)
    raju.sort()
    print(raju)
    raju.pop()
    raju.reverse()
    print(raju)

    # raju.append(1)
    # raju.append(2)
    # raju.insert(1 , 3)

    # print(list[raju])