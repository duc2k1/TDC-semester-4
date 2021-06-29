//
//  RatingControl.swift
//  FoodManager
//
//  Created by CNTT on 5/7/21.
//  Copyright © 2021 fit.tdc. All rights reserved.
//

import UIKit

@IBDesignable class RatingControl: UIStackView {
    //MARK: properties
    private var ratingButtons=[UIButton]()
    @IBInspectable var ratingValue:Int=2 {
        didSet{
            updateRatingButtonState()
        }
    }
    @IBInspectable var starNumber:Int=5 {
        //auto run when change value
        didSet {
            setupRatingCtrol()
        }
    }
    @IBInspectable var starSize:CGSize=CGSize(width: 44.0, height: 44.0){
        didSet {
            setupRatingCtrol()
        }
    }
    //MARK: init
    override init(frame: CGRect) {
        super.init(frame:frame)
        setupRatingCtrol()
    }
    required init(coder: NSCoder) {
        super.init(coder: coder)
        setupRatingCtrol()
    }
    //MARK: rating button initialization
    private func setupRatingCtrol(){
        //load the rating image
        let bundle=Bundle(for:type(of: self))
        let normal=UIImage(named: "normal",in: bundle,compatibleWith: .none)
        let pressed=UIImage(named: "pressed",in: bundle,compatibleWith: .none)
        let selected=UIImage(named: "selected",in:bundle,compatibleWith: .none)
        //remove old rating button
        for button in ratingButtons{
            removeArrangedSubview(button)
            button.removeFromSuperview()
        }
        ratingButtons.removeAll()
        //add
        for _ in 0..<starNumber {
            //Create rating button
            let button=UIButton()
            button.setImage(normal, for:.normal)
            button.setImage(pressed, for:.highlighted)
            button.setImage(selected, for:.selected)
            //setup button's attribute
            button.heightAnchor.constraint(equalToConstant: starSize.height).isActive=false
            button.widthAnchor.constraint(equalToConstant: starSize.width).isActive=false
            //add event processing
            button.addTarget(self, action: #selector(ratingButtonPressed(button:)), for: .touchUpInside)
            //add the button
            addArrangedSubview(button)
            //add the button to the array to manage them
            ratingButtons+=[button]
        }
        //update rating button state
        updateRatingButtonState()
    }
    //MARK event proccessing for each button
    @objc private func ratingButtonPressed(button:UIButton){
        if let index=ratingButtons.firstIndex(of: button){
            print("Button number: \(index+1)")
            //
            if ratingValue==index+1 {
                ratingValue-=1
            }else{
                ratingValue=index+1
            }
            updateRatingButtonState()
        }
    }
    private func updateRatingButtonState(){
        //update the rating button state
        for (i, button) in ratingButtons.enumerated() {
            button.isSelected=i<ratingValue
        }
    }
}
