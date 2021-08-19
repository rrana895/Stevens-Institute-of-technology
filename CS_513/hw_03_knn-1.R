#### Knowledge Discovery and Data Mining (CS 513A)####
# Assignment 2 -breast-cancer-wisconsin.data #

# Course  : CS 513 - A
# First Name : Rachi
# Last Name : Rana
#Id : 10455300
# Purpose : Assignment 3-knn breast-cancer-wisconsin.data

####### *************************************************** ########
rm(list = ls())

# load dataset
cancer <- read.csv("~/Desktop/R csv/breast-cancer-wisconsin.data.csv", na.strings = "?")
View(cancer)

# build dataframe
cancer$F1 <- factor(cancer$F1)
cancer$F2 <- factor(cancer$F2)
cancer$F3 <- factor(cancer$F3)
cancer$F4 <- factor(cancer$F4)
cancer$F5 <- factor(cancer$F5)
cancer$F6 <- factor(cancer$F6)
cancer$F7 <- factor(cancer$F7)
cancer$F8 <- factor(cancer$F8)
cancer$F9 <- factor(cancer$F9)
cancer$Sample <- factor(cancer$Sample)
cancer$Class <- factor(cancer$Class)

cancer_missing <- na.omit(cancer)
View(cancer_missing)
cancer_missing= as.data.frame(cancer_missing) 



idx<-sort(sample(nrow(cancer_missing),as.integer(.70*nrow(cancer_missing))))

training<-cancer_missing[idx,]

test<-cancer_missing[-idx,]

install.packages("kknn")
#load library
library(kknn)

#k = 3
predict_k <- kknn(formula=as.factor(Class)~., training, test, k=3,kernel ="rectangular")

fit <- fitted(predict_k)
table(test$Class,fit)


#k = 5
predict_k <- kknn(formula=as.factor(Class)~., training, test, k=5,kernel ="rectangular")

fit <- fitted(predict_k)
table(test$Class,fit)


#k = 10
predict_k <- kknn(formula=as.factor(Class)~., training, test, k=10,kernel ="rectangular")

fit <- fitted(predict_k)
table(test$Class,fit)
