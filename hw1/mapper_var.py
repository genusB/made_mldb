import sys
import csv


def main():
    x2_sum = 0
    x_sum = 0
    chunk_cnt = 0
    PRICE_COL = 9

    for line in csv.reader(sys.stdin):
        try:
            price = float(line[PRICE_COL])
            x2_sum += price ** 2
            x_sum += price
            chunk_cnt += 1
        except ValueError:
            continue

    chunk_mean = x_sum / float(chunk_cnt)
    chunk_variance = (x2_sum - chunk_cnt * chunk_mean ** 2) / chunk_cnt

    print(chunk_cnt, chunk_mean, chunk_variance)


if __name__ == '__main__':
    main()
