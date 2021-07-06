//
//  DataService.swift
//  News
//
//  Created by CNTT on 06/04/21.
//

class DataService
{
    static var shareddataobj = DataService()
    
    //Khai bao mang category
    private var categoryarray = [Category(titleimgname: "Health.png", backgroundimagename: "running.png"),
    Category(titleimgname: "Science.png", backgroundimagename: "microscope.png"),
    Category(titleimgname: "Entertainment.png", backgroundimagename: "party.png"),
    Category(titleimgname: "Technology.png", backgroundimagename: "tech.png")]
    func getCategoryarray() -> [Category]
    {
        return categoryarray
    }
}
