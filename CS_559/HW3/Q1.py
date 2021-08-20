import numpy as np
import pandas as pd
from sklearn.model_selection import train_test_split
from sklearn.decomposition import PCA 
from numpy.random import RandomState
import math


def Merge(X,y):
    list=[]
    i=0
    for row in X:
        r=[]
        for j in range(len(row)):
            r.append(row[j])
        r.append(y[i])
        list.append(r)
        i=i+1
    return list


def SplitData():
    df = pd.read_csv("pima.csv")
    X=df.drop(columns=['y']).values.tolist()
    y=df.drop(columns=['x1','x2','x3', 'x4','x5','x6', 'x7','x8']).values.reshape(-1).tolist()
    X_train, X_test, y_train, y_test = train_test_split(X, y, test_size=0.50)
    return (X_train, X_test, y_train, y_test)

def RunPCA(X_train, X_test):
    pca = PCA (n_components =3)
    X_train = pca.fit_transform(X_train)
    X_test = pca.fit_transform(X_test)
    return(X_train, X_test)

def MLEClassifier(train ,test):
    c=0
    w=0
    
    train0=train[train['y']==0].values.tolist()
    train1=train[train['y']==1].values.tolist()
    test=test.values.tolist()

    mean0=np.mean(train0, axis=0)
    var0=np.var(train0, axis=0)
    mean1=np.mean(train1, axis=0)
    var1=np.var(train1, axis=0)

    prior0=len(train0)
    prior1=len(train1)

    for row in test:
        likelihood0=1.0
        likelihood1=1.0
        for i in range(3):
            likelihood0  *=  ( (1.0/(math.sqrt(2*3.1415*var0[i]))) * math.exp((-0.5)*((row[i]-mean0[i])**2)/var0[i]))
            likelihood1  *=  ( (1.0/(math.sqrt(2*3.1415*var1[i]))) * math.exp((-0.5)*((row[i]-mean1[i])**2)/var1[i]))
        posterior0=prior0 * likelihood0
        posterior1=prior1 * likelihood1
        if(posterior0 > posterior1 and row[3]==0):
            c+=1
        elif(posterior0 < posterior1 and row[3]==1):
            c+=1
        else:
            w+=1
    return(c,w)


def findAccuracy(y_test, y_pred):
    correct=0
    wrong=0
    for i in range(len(y_pred)):
        if(y_test[i]==y_pred[i]):
            correct+=1
        else:
            wrong+=1
    return(correct, wrong)




LA=[]
for i in range(10):
    X_train, X_test, y_train, y_test=SplitData()
    X_train, X_test = RunPCA (X_train, X_test)
    train = Merge(X_train, y_train)
    test  = Merge(X_test, y_test)
    train = pd.DataFrame(train, columns = ['x1' , 'x2', 'x3', 'y'])
    test = pd.DataFrame(test, columns = ['x1' , 'x2', 'x3', 'y'])
    c, w= MLEClassifier(train, test)
    LA.append(float(c)/(c+w)*100)

MA=np.average(LA)
sd=np.std(LA)
print ("Accuracy="+ str(MA) )
