def print_formatted(number):

    width =len(bin(number)[2:])

    for i in range(0,n):
        a = str(i + 1). rjust(width)
        b = str(oct(i+1)[2:]).rjust(width)
        c = str(hex(i +1)[2:]). rjust(width).upper()
        d = str(bin(i + 1)[2 :]). rjust(width)
        print(f"{a} {b} {c} {d}")
    

if __name__ == '__main__':
    n = int(input())
    print_formatted(n)