
import pandas as pd



if __name__ == '__main__':
    df = pd.read_csv("Lotto.csv")
    print(df.corr())
