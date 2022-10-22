import sys


def main():
    chunk_cnt = 0
    mean_of_all = 0

    for line in sys.stdin:
        chunk, mean = line.split()
        chunk = int(chunk)
        mean = float(mean)
        mean_of_all = (mean_of_all * chunk_cnt + chunk * mean) / (chunk + chunk_cnt)
        chunk_cnt += chunk

    print(mean_of_all)


if __name__ == '__main__':
    main()