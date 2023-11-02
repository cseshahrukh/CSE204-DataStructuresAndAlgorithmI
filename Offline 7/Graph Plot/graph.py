# -*- coding: utf-8 -*-
"""
Created on Mon May 31 20:45:20 2021

@author: USER
"""

import matplotlib.pyplot as pp
import numpy as np 

data=np.loadtxt(fname = 'F:/Level 2 Term 1/CSE 204 DS Lab/Offline 7/Graph Plot/ascending merge.txt', delimiter=',')
print(data)

X=[]
Y=[]

for i in range(len(data)):
    for j in range (2): 
        if j==0:
            X.append(data[i][j])
        else :
            Y.append(data[i][j])
print(X)
print(Y)




data2=np.loadtxt(fname = 'F:/Level 2 Term 1/CSE 204 DS Lab/Offline 7/Graph Plot/ascending quick.txt', delimiter=',')
print(data2)

X2=[]
Y2=[]

for i in range(len(data2)):
    for j in range (2): 
        if j==0:
            X2.append(data2[i][j])
        else :
            Y2.append(data2[i][j])

data3=np.loadtxt(fname = 'F:/Level 2 Term 1/CSE 204 DS Lab/Offline 7/Graph Plot/descending merge.txt', delimiter=',')


X3=[]
Y3=[]

for i in range(len(data3)):
    for j in range (2): 
        if j==0:
            X3.append(data3[i][j])
        else :
            Y3.append(data3[i][j])

data4=np.loadtxt(fname = 'F:/Level 2 Term 1/CSE 204 DS Lab/Offline 7/Graph Plot/descending quick.txt', delimiter=',')


X4=[]
Y4=[]

for i in range(len(data4)):
    for j in range (2): 
        if j==0:
            X4.append(data4[i][j])
        else :
            Y4.append(data4[i][j])

data5=np.loadtxt(fname = 'F:/Level 2 Term 1/CSE 204 DS Lab/Offline 7/Graph Plot/random merge.txt', delimiter=',')


X5=[]
Y5=[]

for i in range(len(data5)):
    for j in range (2): 
        if j==0:
            X5.append(data5[i][j])
        else :
            Y5.append(data5[i][j])
            
            

data6=np.loadtxt(fname = 'F:/Level 2 Term 1/CSE 204 DS Lab/Offline 7/Graph Plot/random quick.txt', delimiter=',')


X6=[]
Y6=[]

for i in range(len(data6)):
    for j in range (2): 
        if j==0:
            X6.append(data6[i][j])
        else :
            Y6.append(data6[i][j])




pp.plot(X, Y, label='Ascending Merge')
pp.plot(X2, Y2, label='Ascending Quick')
pp.plot(X3, Y3, label='Descending Merge')
pp.plot(X4, Y4, label='Descending Quick')
pp.plot(X5, Y5, label='Random Merge')
pp.plot(X6, Y6, label='Random Quick')

pp.xlabel("Array Size 10^n")
pp.ylabel("Time in milliseconds")
pp.title('Data')


pp.legend()
pp.show()