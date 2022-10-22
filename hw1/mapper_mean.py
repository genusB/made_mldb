import sys
import csv


def main():
    chunk_cnt = 0
    sum_of_all = 0
    PRICE_COL = 9

    for line in csv.reader(sys.stdin):
        try:
            price = float(line[PRICE_COL])
            sum_of_all += price
            chunk_cnt += 1
        except ValueError:
            continue

    mean = sum_of_all / chunk_cnt
    print(chunk_cnt, mean)


if __name__ == '__main__':
    main()
