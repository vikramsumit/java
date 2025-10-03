# import sys

# # Standard 7-segment patterns for digits 0-9 and some operators
# # Each pattern is a tuple of three strings, each of length 3
# PATTERNS = {
#     '0': (" _ ",
#           "| |",
#           "|_|"),
#     '1': ("   ",
#           "  |",
#           "  |"),
#     '2': (" _ ",
#           " _|",
#           "|_ "),
#     '3': (" _ ",
#           " _|",
#           " _|"),
#     '4': ("   ",
#           "|_|",
#           "  |"),
#     '5': (" _ ",
#           "|_ ",
#           " _|"),
#     '6': (" _ ",
#           "|_ ",
#           "|_|"),
#     '7': (" _ ",
#           "  |",
#           "  |"),
#     '8': (" _ ",
#           "|_|",
#           "|_|"),
#     '9': (" _ ",
#           "|_|",
#           " _|"),

#     '+': ("   ",
#           " _ ",
#           " | "),
#     '-': ("   ",
#           " _ ",
#           "   "),
#     '=': ("   ",
#           " _ ",
#           " _ "),
#     '*': ("   ",
#           "\\ /",
#           " /\\"),
#     '%': ("%  ",
#           "   ",
#           "  %")
# }

# PAT_EXACT = { PATTERNS[k]: k for k in PATTERNS }

# def read_input():
#     data = sys.stdin.read().splitlines()
#     if not data: 
#         return None
#     idx = 0
#     while idx < len(data) and data[idx].strip() == "":
#         idx += 1
#     if idx >= len(data):
#         return None
#     N = int(data[idx].strip()); idx += 1
#     rows = []
#     while len(rows) < 3 and idx < len(data):
#         line = data[idx].rstrip("\n")
#         rows.append(line)
#         idx += 1
#     for i in range(3):
#         if len(rows[i]) < 3*N:
#             rows[i] = rows[i].ljust(3*N)
#         else:
#             rows[i] = rows[i][:3*N]
#     return N, rows

# def slice_blocks(N, rows):
#     blocks = []
#     for pos in range(N):
#         c0 = 3*pos
#         c1 = c0 + 3
#         block = (rows[0][c0:c1], rows[1][c0:c1], rows[2][c0:c1])
#         blocks.append(block)
#     return blocks

# def diff_cells(a, b):
#     diff = 0
#     for r in range(3):
#         for c in range(3):
#             if a[r][c] != b[r][c]:
#                 diff += 1
#     return diff

# def pattern_to_exact_char(block):
#     return PAT_EXACT.get(block, None)

# def evaluate_ltr(tokens):
#     if not tokens:
#         return None
#     try:
#         val = int(tokens[0])
#     except:
#         return None
#     i = 1
#     while i < len(tokens):
#         op = tokens[i]; nxt = tokens[i+1]
#         try:
#             num = int(nxt)
#         except:
#             return None
#         if op == '+':
#             val = val + num
#         elif op == '-':
#             val = val - num
#         elif op == '*':
#             val = val * num
#         elif op == '%':
#             if num == 0:
#                 return None
#             val = val % num
#         else:
#             return None
#         i += 2
#     return val

# def main():
#     inp = read_input()
#     if inp is None:
#         return
#     N, rows = inp
#     blocks = slice_blocks(N, rows)

#     exact_chars = [pattern_to_exact_char(b) for b in blocks]

#     candidates = []
#     for i in range(N):
#         b = blocks[i]
#         cands = []
#         for ch, pat in PATTERNS.items():
#             d = diff_cells(b, pat)
#             if d == 1:
#                 cands.append(ch)
#         candidates.append(cands)

#     for p in range(N):
#         ok = True
#         for i in range(N):
#             if i == p: continue
#             if exact_chars[i] is None:
#                 ok = False
#                 break
#         if not ok:
#             continue
#         for ch in candidates[p]:
#             chars = []
#             for i in range(N):
#                 if i == p:
#                     chars.append(ch)
#                 else:
#                     chars.append(exact_chars[i])
#             if '=' not in chars:
#                 continue
#             eq_pos = chars.index('=')
#             rhs_chars = chars[eq_pos+1:]
#             lhs_chars = chars[:eq_pos]
#             if len(rhs_chars) == 0 or len(lhs_chars) == 0:
#                 continue
#             if not all(c is not None and c.isdigit() for c in rhs_chars):
#                 continue

#             tokens = []
#             cur_num = ""
#             valid_lhs = True
#             for c in lhs_chars:
#                 if c is None:
#                     valid_lhs = False; break
#                 if c.isdigit():
#                     cur_num += c
#                 else:
#                     if cur_num == "":
#                         valid_lhs = False; break
#                     tokens.append(cur_num)
#                     tokens.append(c)
#                     cur_num = ""
#             if not valid_lhs:
#                 continue
#             if cur_num == "":
#                 continue
#             tokens.append(cur_num)
#             if len(tokens) % 2 == 0:
#                 continue
#             val_lhs = evaluate_ltr(tokens)
#             if val_lhs is None:
#                 continue
#             rhs_value = int("".join(rhs_chars))
#             if val_lhs == rhs_value:
#                 print(p+1)  
#                 return
#     print(-1)

# if __name__ == "__main__":
#     main()
