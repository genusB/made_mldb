import sys
import csv


def main():
    chunk_cnt = 0
    chunk_mean = 0
    chunk_variance = 0
    PRICE_COL = 9

    for line in csv.reader(sys.stdin):
        try:
            price = float(line[PRICE_COL])
            chunk_variance = chunk_cnt * ((price - chunk_mean) / (chunk_cnt + 1)) ** 2 + \
                             (chunk_cnt * chunk_variance) / (chunk_cnt + 1)
            chunk_mean = (chunk_mean * chunk_cnt + price) / (chunk_cnt + 1)
            chunk_cnt += 1
        except ValueError:
            continue

    print(chunk_cnt, chunk_mean, chunk_variance)


if __name__ == '__main__':
    main()
