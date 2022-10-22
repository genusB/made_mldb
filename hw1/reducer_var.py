import sys


def main():
    chunk_cnt = 0
    variance_of_all = 0
    mean_of_all = 0

    for line in sys.stdin:
        chunk, mean, variance = line.split()
        chunk = int(chunk)
        mean = float(mean)
        variance = float(variance)
        mean_of_all = (mean_of_all * chunk_cnt + chunk * mean) / (chunk + chunk_cnt)
        variance_of_all = (chunk * variance + chunk_cnt * variance_of_all) / (chunk + chunk_cnt) + \
                          chunk * chunk_cnt * ((mean_of_all - mean) / (chunk + chunk_cnt)) ** 2
        chunk_cnt += chunk

    print(variance_of_all)


if __name__ == '__main__':
    main()
