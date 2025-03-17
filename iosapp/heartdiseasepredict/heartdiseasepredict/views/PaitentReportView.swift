//
//  PaitentReportView.swift
//  heartdiseasepredict
//
//  Created by gopichand on 15/03/25.
//

import Foundation
import SwiftUI

struct PaitentReportView: View{
    
    @State private var paitentName: String = ""
    @State private var date = Date()
    @State private var ageComponents:DateComponents = DateComponents()
    @State private var chestPainType:Int = 0
    @State private var trestbps:String = ""
    @State private var chol:String=""
    @State private var sugarLevelAbove120: Bool = false
    @State private var inducedAngina = false
    @State private var restEcg:Int = 0
    @State private var thalach:String = ""
    @State private var stDepressionString = ""
    @State private var slopePeak:Int = 0
    @State private var fluroroscopy: Int = 0
    @State private var emailOrPhone: String = ""
    @State private var thal:Int = 0
    
    @State private var showAlert: Bool = false
    
//    @Binding var paitentReport: PaitentReport
    
    //chestpains lis
    let chestPainTypeList = [
        ChestPainTypes(id:0,name:"Typical angina"),
        ChestPainTypes(id:1,name:"Atypical angina"),
        ChestPainTypes(id:2,name:"Non-anginal pain"),
        ChestPainTypes(id:3,name:"Asymptomic")
    ]
    
    let rectEcgList = [
        RestEcg(id: 0, name:"Normal"),
        RestEcg(id:1, name: "Having ST-T were abnormility"),
        RestEcg(id:2, name:"Showing probable or definite left ventricular hypertropy")
    ]
    
    let sloapPeakList = [
        SloapPeak(id:0, name: "Upsloaping"),
        SloapPeak(id:1, name: "Flat"),
        SloapPeak(id:2, name: "Downsloaping")
    ]
    var body: some View {
        NavigationStack{
            Form{
                Section("Paitent Details"){
                    DatePicker("Date of birth", selection:$date,
                               in:...Date(),displayedComponents:.date).datePickerStyle(DefaultDatePickerStyle()).font(.title3)
                    TextField("paitent name", text: $paitentName)
                    TextField("eamil or phonenumer", text:$emailOrPhone)
                }
                Section("Patient Test Results"){
                
                    Picker("Chest pain type",selection: $chestPainType){
                        ForEach(chestPainTypeList){
                            frequency in Text(frequency.name)
                        }
                    }
                    TextField("Resting blood pressure in mm/hg", text:$trestbps)
                    TextField("Serum cholestrol in mg/dl", text:$chol)
                    Toggle("Fasting blood sugar above 120mg/dl", isOn: $sugarLevelAbove120)
                    Picker("Resting electrocardiographic results", selection:$restEcg){
                        ForEach(rectEcgList) { list in
                            Text(list.name)
                        }
                    }
                    TextField("Heart rate during a stress test",text:$thalach)
                    Toggle("Exercise induced angina", isOn: $inducedAngina)
                    TextField("ST Depression induced by exercise relative to rest", text:$stDepressionString)
                    Picker("sloap of the peak exercise releative to rest",selection: $slopePeak){
                        ForEach(sloapPeakList){
                            slop in
                            Text(slop.name)
                        }
                    }
                    Picker("Number of major vessels (0-4) colored by fluroroscopy",selection: $fluroroscopy){
                        ForEach(0..<5){
                            i in Text("\(i)")
                        }
                    }
                    Picker("Thalium stress test (0-3)",selection: $thal){
                        ForEach(0..<4){
                            i in Text("\(i)")
                        }
                    }
                    Button("Submit"){
                        
                        let dateFormatter = DateFormatter()
                        dateFormatter.dateFormat = "dd/MM/yyyy"
                        guard let birthDate = dateFormatter.date(from: dateFormatter.string(from: date)) else {return}
                        ageComponents = Calendar.current.dateComponents([.year, .month, .day], from: birthDate, to: Date())
                        dataSumbit()
                    }.padding(.all,12)
                        .foregroundColor(.white)
                        .background(Color.green)
                        .alert(isPresented: $showAlert){
                            Alert(title: Text("Hear disease"),
                                  message: Text("Paitent have heart disease"),
                                  dismissButton: .default(Text("ok"))
                            )
                            
                        }
                }.navigationTitle("Heart Diease Predictor")
            }
        }
    }
    
    func dataSumbit(){
        let val = PaitentReport(id: 1, age: ageComponents.year!, gender: "FEMALE", chestpain: chestPainType, title: "Normal", trestbp: Int(trestbps) ?? 0, chol: Int(chol) ?? 0, fbs: sugarLevelAbove120, restecg: restEcg, thalach: Int(thalach) ?? 0, exang: inducedAngina, oldpeak: Float(stDepressionString) ?? 0, sloap:slopePeak, ca: fluroroscopy, thal: Int(thal) ?? 0)
        print("paitent object", val)
        guard let jsonString = try? JSONEncoder().encode(val) else { return }
        let jsonData = String( data: jsonString, encoding: .utf8)
        print("Triggering predict service")
        guard let url = URL(string: "http://localhost:8080/v1/heart/disease/predict") else {
            print("Not a valid URL :: Please check the URL")
            return
        }
        var request = URLRequest(url:url)
        request.httpMethod = "POST"
        request.setValue("application/json",forHTTPHeaderField: "Content-Type")
        request.httpBody = jsonString
        print("Request object :: ",request)
        let task = URLSession.shared.dataTask(with: request){
            data, response, error in
            if let error = error{
                print("Error with request: \(error)")
                return
            }
            //Handle successfull response
            if let data = data {
                print("data::",data)
                if let responseData = try? JSONDecoder().decode(ResponseData.self, from:data){
                    
                    print("predicted value: ",responseData)
                    if(responseData.code == 100){
                        showAlert = true
                    }
                }else {
                    print("Error decoding response")
                }
            }
        }
        print("Task is Initiated",task.resume())
    }
}

struct PaitentReport_View: PreviewProvider{
    static var previews: some View{
        NavigationStack{
            PaitentReportView()
        }
    }
}

