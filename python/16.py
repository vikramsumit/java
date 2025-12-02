if __name__ == '__main__':
    n = int(input())
    student_marks = {}
    for _ in range(n):
        name, *line = input().split()
        scores = list(map(float, line))
        student_marks[name] = scores
    query_name = input()

    # score = student_marks(query_name)

    avg = sum(student_marks[query_name])/len(scores)
    print(f"{avg:.2f}")