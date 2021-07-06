//
//  CategoryVC.swift
//  News
//
//  Created by Ho Viet Long on 5/6/21.
//

import UIKit

class CategoryVC: UIViewController,UICollectionViewDelegate,UICollectionViewDataSource {
   
    //index duoc truyen sang CategoryNewsVC
    var index: Int!
    
    @IBOutlet weak var categoryCollectionView: UICollectionView!
    
    override func viewDidLoad() {
        super.viewDidLoad()
        categoryCollectionView.delegate = self
        categoryCollectionView.dataSource = self
    }
    func collectionView(_ collectionView: UICollectionView, numberOfItemsInSection section: Int) -> Int {
        return DataService.shareddataobj.getCategoryarray().count
    }
    //Custom category include: name, image
    func collectionView(_ collectionView: UICollectionView, cellForItemAt indexPath: IndexPath) -> UICollectionViewCell {
        if let cell = collectionView.dequeueReusableCell(withReuseIdentifier: "grid", for: indexPath) as? CategoryCollectionCellCollectionViewCell
        {
            let array = DataService.shareddataobj.getCategoryarray()
            cell.updateCell(imagename: array[indexPath.row].backgroundimagename, categorytitleimage: array[indexPath.row].titleimgname)
            cell.layer.cornerRadius = 8
            cell.layer.borderColor = #colorLiteral(red: 0, green: 0, blue: 0, alpha: 1)
            cell.layer.borderWidth = 4
            return cell
        }
        return UICollectionViewCell()
    }
    
    //Hien thi collection gom 4 category
    func collectionView(_ collectionView: UICollectionView, didSelectItemAt indexPath: IndexPath) {
        
        if indexPath.row == 0
        {
            index = 0
            performSegue(withIdentifier: "categorysegue", sender: self)
        }
        
        
        else if indexPath.row == 1
        {
            index = 1
            performSegue(withIdentifier: "categorysegue", sender: self)
        }
        else if indexPath.row == 2
        {
            index = 2
            performSegue(withIdentifier: "categorysegue", sender: self)
        }
        else if indexPath.row == 3
        {
            index = 3
            performSegue(withIdentifier: "categorysegue", sender: self)
        }
        
    }
    //Truyen tham so cua category de hien thi bai viet tuong ung
    //Truyen sang CategoryNewsVC
    override func prepare(for segue: UIStoryboardSegue, sender: Any?) {
        if let destinationVC = segue.destination as? CategoryNewsVC        {
            destinationVC.i = index
        }
    }
}
