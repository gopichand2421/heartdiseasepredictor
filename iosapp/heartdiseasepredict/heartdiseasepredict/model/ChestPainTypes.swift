//
//  ChestPainTypes.swift
//  heartdiseasepredict
//
//  Created by gopichand on 16/03/25.
//

import Foundation

//chestpain type model
struct ChestPainTypes: Identifiable{
    var id: Int
    var name: String
    
    init(id:Int, name:String){
        self.id = id
        self.name = name
    }
}
