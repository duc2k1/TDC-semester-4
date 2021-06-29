//
//  ViewController.swift
//  FoodManager
//
//  Created by CNTT on 4/16/21.
//  Copyright © 2021 fit.tdc. All rights reserved.
//

import UIKit

class ViewController: UIViewController,UITextFieldDelegate,UIImagePickerControllerDelegate,UINavigationControllerDelegate {
    //MARK: Properties
    @IBOutlet weak var edtMealName: UITextField!
    @IBOutlet weak var imageView: UIImageView!
    override func viewDidLoad() {
        super.viewDidLoad()
        edtMealName.delegate=self
    }
    //MARK: TextField's Delegate Functions
    func textFieldShouldReturn(_ textField: UITextField) -> Bool {
        textField.resignFirstResponder()
        
        return true
    }
    func textFieldDidEndEditing(_ textField: UITextField) {
        if let mealName=textField.text{
            print("Meal name is: \(mealName)")
        }
    }
    //MARK: Image processing
    @IBAction func imageProcessing(_ sender: UITapGestureRecognizer) {
        //hide the keyboard
        edtMealName.resignFirstResponder()
        //get image from photo libary
        let imagePicker = UIImagePickerController()
        //config the images source for the picker
        imagePicker.sourceType = .photoLibrary
        //delegation
        imagePicker.delegate=self
        //show the image picker
        present(imagePicker,animated: true,completion: nil)
        
    }
    func imagePickerController(_ picker: UIImagePickerController, didFinishPickingMediaWithInfo info: [UIImagePickerController.InfoKey : Any]) {
        if let selectedImage = info[UIImagePickerController.InfoKey.originalImage] as? UIImage{
            imageView.image=selectedImage
            dismiss(animated: true, completion: nil)
        }
    }
}

