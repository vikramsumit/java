from itertools import product 

A = []
B = []

A = map(int, input().split())
B = map(int, input().split())

print(*product(A,B))