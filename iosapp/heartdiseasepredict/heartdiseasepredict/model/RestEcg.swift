//
//  RestEcg.swift
//  heartdiseasepredict
//
//  Created by gopichand on 16/03/25.
//

import Foundation

struct RestEcg: Identifiable {
    var id:Int
    var name:String
    
    init(id:Int, name:String){
        self.id = id
        self.name = name
    }
}
