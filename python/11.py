if __name__ == '__main__':
    students = []

    for _ in range(int(input())):
        name = input()
        score = float(input())
        students.append([name, score])

    score = sorted(set([g for _, g in students]))

    second_lowest_marks = score[1]

    names = sorted([name for name, g in students if g == second_lowest_marks])

    for name in names:
        print(name)