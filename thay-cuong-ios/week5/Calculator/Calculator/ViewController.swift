//
//  ViewController.swift
//  Calculator
//
//  Created by Ho Viet Long on 3/23/21.
//  Copyright © 2021 Ho Viet Long. All rights reserved.
//

import UIKit

class ViewController: UIViewController {
    //MARK: Properties

    @IBOutlet weak var calScr: UILabel!
    var isFirst = true
    var flag = true

    override func viewDidLoad() {
        super.viewDidLoad()
        // Do any additional setup after loading the view, typically from a nib.
    }
    //MARK: button actions

    @IBAction func buttonPressed(_ sender: UIButton) {
        let digit = sender.currentTitle!
        
        if(isFirst)
        {
            if(digit != "0")
            {
                calScr.text = digit
                isFirst = false
            }else{
                calScr.text = digit
            }
        }
        else
        {
            let currDisplay = calScr.text!
            calScr.text = currDisplay + digit
        }

    }
    //MARK: Calculated Variable
    var calScreenDoubleValue: Double {
        get {
            if let stringValue = calScr.text {
                if let doubleValue = Double(stringValue) {
                    return doubleValue
                }
                else {
                    return 0
                }
            }
            else {
                return 0
            }
        } set {
            calScr.text = String(newValue)
        }
    }
    //MARK: Math button
    //Khoi tao lop model
    var calModel = CalculatorModel()
    @IBAction func calFunctions(_ sender: UIButton) {
        //Goi phuong thuc set so hang
        calModel.setOperand(operand: calScreenDoubleValue)
        if let mathSymbol = sender.currentTitle {
            //Goi phuong thuc tinh toan
            calModel.calculate(mathOperator: mathSymbol)
        }
        
        if let value = calModel.result {
            calScreenDoubleValue = value
        }
        isFirst = true
    }

}


