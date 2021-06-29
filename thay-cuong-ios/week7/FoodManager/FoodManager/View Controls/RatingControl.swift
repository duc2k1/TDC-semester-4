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
    //MARK: init
    override init(frame: CGRect) {
        super.init(frame:frame)
        setupRatingCtrol()
    }
    
    required init(coder: NSCoder) {
        super.init(coder: coder)
        setupRatingCtrol()
    }
    private func setupRatingCtrol(){
        //load the rating image
        let bundle=Bundle(for:type(of: self))
        let normal=UIImage(named: "normal",in: bundle,compatibleWith: .none)
        let pressed=UIImage(named: "pressed",in: bundle,compatibleWith: .none)
        let selected=UIImage(named: "selected",in:bundle,compatibleWith: .none)
        for _ in 0..<5 {
            //Create rating button
            let button=UIButton()
            button.setImage(normal, for:.normal)
            button.setImage(pressed, for:.highlighted)
            button.setImage(selected, for:.selected)
            //setup button's attribute
            button.heightAnchor.constraint(equalToConstant: 44.0).isActive=true
            button.widthAnchor.constraint(equalToConstant: 44.0).isActive=true
            //add event processing
            button.addTarget(self, action: #selector(ratingButtonPressed(button:)), for: .touchUpInside)
            //add the button
            addArrangedSubview(button)
            //add the button to the array to manage them
            ratingButtons+=[button]
        }
    }
    //MARK event proccessing for each button
    @objc private func ratingButtonPressed(button:UIButton){
        if let index=ratingButtons.firstIndex(of: button){
            print("Button number: \(index+1)")
        }

    }
}
