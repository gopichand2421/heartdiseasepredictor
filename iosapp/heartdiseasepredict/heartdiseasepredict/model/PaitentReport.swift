//
//  PaitentReport.swift
//  heartdiseasepredict
//
//  Created by gopichand on 15/03/25.
//

import Foundation

struct PaitentReport: Identifiable, Encodable{
    
    var id: Int
    var title: String
    var age: Int
    var gender: String
    var chestpain: Int
    var trestbp: Int
    var chol: Int
    var fbs: Bool
    var restecg: Int
    var thalach: Int
    var exang: Bool
    var oldpeak: Float
    var sloap: Int
    var ca: Int
    var thal: Int
    
    init(id:Int, age: Int, gender: String, chestpain:Int, title: String,
         trestbp: Int, chol: Int, fbs:Bool, restecg: Int, thalach:Int, exang:Bool, oldpeak:Float,
         sloap:Int, ca:Int, thal:Int){
        self.id = id
        self.title = title
        self.age = age
        self.gender = gender
        self.chestpain = chestpain
        self.trestbp = trestbp
        self.chol = chol
        self.fbs = fbs
        self.restecg = restecg
        self.thalach = thalach
        self.exang = exang
        self.oldpeak = oldpeak
        self.sloap = sloap
        self.ca = ca
        self.thal = thal
    }
}
