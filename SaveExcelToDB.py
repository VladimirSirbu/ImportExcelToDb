import pandas as pd
import sys
from sqlalchemy import create_engine

def getDataFromJava(file_path, file_format, db_url, table_name):

    if (file_format == '.csv'):
        df = pd.read_csv(file_path)
    elif ((file_format == '.xls') or (file_format == '.xlsx')):
        df = pd.read_excel(file_path)

    engine = create_engine(db_url)
    df.to_sql(table_name, con=engine)
    return

file_path = sys.argv[1]
file_format = sys.argv[2]
db_url = sys.argv[3]
table_name = sys.argv[4]

getDataFromJava(file_path, file_format, db_url, table_name)


# pip install psycopg2-binary
# pip install xlrd
# pip install Flask-SQLAlchemy
# pip install pandas



