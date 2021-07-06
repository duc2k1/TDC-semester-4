//
//  ArticleDL+CoreDataProperties.swift
//  News
//
//  Created by Ho Viet Long on 5/7/21.
//
//

import CoreData

extension ArticleDL {

    @nonobjc public class func fetchRequest() -> NSFetchRequest<ArticleDL> {
        return NSFetchRequest<ArticleDL>(entityName: "ArticleDL")
    }

    @NSManaged public var title: String?
    @NSManaged public var content: String?
    @NSManaged public var url: String?

}
