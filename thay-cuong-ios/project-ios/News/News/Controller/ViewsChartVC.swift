//
//  MostViewedController.swift
//  News
//
//  Created by Duc on 6/17/21.
//

import Charts //tao bieu do
import Firebase

class ViewsChartVC: UIViewController, ChartViewDelegate {
    var barChart=BarChartView()
    //
    override func viewDidLoad() {
        barChart.delegate=self
    }
    override func viewDidLayoutSubviews() {
        super.viewDidLayoutSubviews()
        let database=Database.database().reference()
        //get id iphone
        let idIphone=UIDevice.current.identifierForVendor?.uuidString
        //lay du lieu tu firebase bang id iphone
        database.child("views/\(idIphone!)").getData{(error, snapshot) in
            if let error = error {
                print("Error getting data \(error)")
            }
            else if snapshot.exists() {
                let value=snapshot.value as! [String: Int]
                let categories=["Heath","Science","Entertainment","Technology"]
                DispatchQueue.main.async {
                    self.barChart.frame=CGRect(x:0,y:0,width:self.view.frame.size.width,height:self.view.frame.size.width)
                    self.barChart.center=self.view.center
                    self.view.addSubview(self.barChart)
                    var entries=[BarChartDataEntry]()
                    entries.append(BarChartDataEntry(x:Double(0),y:Double(value["heathViews"]!)))
                    entries.append(BarChartDataEntry(x:Double(1),y:Double(value["scienceViews"]!)))
                    entries.append(BarChartDataEntry(x:Double(2),y:Double(value["entertainmentViews"]!)))
                    entries.append(BarChartDataEntry(x:Double(3),y:Double(value["technologyViews"]!)))
                    let set=BarChartDataSet(entries:entries,label:"Categories")
                    set.colors=ChartColorTemplates.joyful()
                    let data=BarChartData(dataSet:set)
                    self.barChart.xAxis.valueFormatter=IndexAxisValueFormatter(values: categories)
                    self.barChart.xAxis.granularity=1
                    self.barChart.xAxis.labelPosition = .bottom
                    self.barChart.data=data
                }
            }
        }
    }
}
