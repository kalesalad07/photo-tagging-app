import mysql.connector
import csv

# dir = r"C:\Users\ISHAAN\OneDrive\Desktop\New DoPy Software"
file_path = "/Users/deepan_roy/Documents/2-2/DBS_Proj/messList.csv"

def cuteify(id):
    if(id[4]== 'H'):
        return 'M'+id[2] + id[3] + id[-4:-1]+id[-1] 
    elif(id[4] == 'P'):
        return 'P'+id[2] + id[3] + id[-4:-1]+id[-1] 
    else:
        return id[2] + id[3] + id[-4:-1]+id[-1]
    

def cleaned(val):
    new_dict = {}
    new_dict['id'] = val['IDNO']
    new_dict['name'] = val['SNAME'].upper()
    new_dict['hostel'] = val['HOSCOD']
    new_dict['room'] = val['ROOM']
    new_dict['cuteid'] = cuteify(val['IDNO'])

    return new_dict




mydb = mysql.connector.connect(
    host = "localhost",
    user = "root",
    password = "pword"
)

mycursor = mydb.cursor()

#reading data
data = []
headers = []
print('reading the file')
with open(file_path) as csv_file:
    csv_reader = csv.reader(csv_file, delimiter=',')
    line_count = 0
    for row in csv_reader:
        if line_count == 0:
            for header in row:
                headers.append(header)            
            line_count += 1
        else:
            obj = {}
            for idx, item in enumerate(row):
                obj[headers[idx]] = item            
            data.append(cleaned(obj))
            line_count += 1
    print(f'Processed {line_count} lines.')

# Select database
query = "use dopy;"
mycursor.execute(query)

squery = "insert into STUDENT (ID, NAME) values (%s, %s)" #data[id],data[name]

hquery = "insert into HOSTELS (HOSTEL_NAME, ROOM_NUM) values (%s, %s)" #data[hostel], data[room]
lquery = "insert into LIVES_IN (HOSTEL_NAME, ROOM_NUM, S_ID) values (%s, %s, %s)" #data[id],data[hostel],data[room]

for obj in data:
    sval = obj["id"], obj["name"]
    hval = obj["hostel"], obj["room"]
    lval = obj["hostel"],obj["room"],obj["id"],

    mycursor.execute(squery,sval)
    try:
        mycursor.execute(hquery,hval)
    except mysql.connector.errors.IntegrityError:
        # print("Duplicate room skipped.")
        pass
    mycursor.execute(lquery,lval)

mydb.commit()