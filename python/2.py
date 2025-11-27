class Solution:
    def studentGrade(self, marks):
        if marks >= 90:
            return 'A'
        elif marks >= 80:
            return 'B'
        elif marks >= 70:
            return 'C'
        elif marks >= 60:
            return 'D'
        else:
            return 'F'
sol = Solution()
marks = int(input())
print(sol.studentGrade(marks))