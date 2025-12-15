# a =  set([1,2,1,2,3,4,5,6,0,9,12,22,3])

# avg = sum(a)/len(a)

# print(avg)

def average(array):
    a = sum(n)/len(n)

    return a

if __name__ == '__main__':
    n = int(input())
    arr = list(map(int, input().split()))
    result = average(arr)
    print(result)