#### Knowledge Discovery and Data Mining (CS 513A)####
# Assignment 2 -breast-cancer-wisconsin.data #

# Course  : CS 513 - A
# First Name : Rachi
# Last Name : Rana
#Id : 10455300
# Purpose : Assignment 4- Naive Bayes breast-cancer-wisconsin.data

####### *************************************************** ########



rm(list = ls())

bcd <- read.csv("~/Desktop/R csv/breast-cancer-wisconsin.data.csv",na.strings = "?")
View(bcd)
str(bcd)

bcd_missing <- na.omit(bcd)
View(bcd_missing)
#convert integer to factor datatypes
bcd_missing[sapply(bcd_missing, is.integer)] <- lapply(bcd_missing[sapply(bcd_missing, is.integer)], as.factor)
str(bcd_missing)

#test and training data sets 
idx<-sort(sample(nrow(bcd_missing),as.integer(.70*nrow(bcd_missing))))
training<-bcd_missing[idx,-1]

test<-bcd_missing[-idx,-1]

#load library
library(class)
library(e1071)
#Naive Bayes
nBayes_all<- naiveBayes(Class~., data=training)
category_all <- predict(nBayes_all,test)

table(nBayes_all=category_all,Class=test$Class)
NB_wrong<-sum(category_all!=test$Class)
NB_errorRate<-NB_wrong/length(category_all)
NB_errorRate

accuracy <- 1-NB_errorRate
accuracy


