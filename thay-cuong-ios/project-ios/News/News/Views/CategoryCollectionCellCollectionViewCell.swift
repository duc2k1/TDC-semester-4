//
//  CategoryCollectionCellCollectionViewCell.swift
//  News
//
//  Created by CNTT on 06/04/21.
//

import UIKit //la 1 module chua class, struct, enum dung de tao UI

class CategoryCollectionCellCollectionViewCell: UICollectionViewCell {
    @IBOutlet weak var categorytitleimg: UIImageView!
    @IBOutlet weak var backgroundimg: UIImageView!
    //
    func updateCell(imagename: String,categorytitleimage: String)
    {
        self.backgroundimg.image = UIImage(named: imagename)
        self.categorytitleimg.image = UIImage(named: categorytitleimage)
    }
}
