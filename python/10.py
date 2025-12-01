if __name__ == '__main__':
    n = int(input())
    arr = list(map(int, input().split()))  

    final = sorted(set(arr))
    # for i in range (len(final)):
    #     if final[-1] == final[-2]:
    #         break
    #     else:
    #         continue
            
    print(final[-2])

    
