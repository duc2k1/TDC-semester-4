//
//  CalculatorModel.swift
//  Calculator
//
//  Created by Ho Viet Long on 4/2/21.
//  Copyright © 2021 Ho Viet Long. All rights reserved.
//

import Foundation
//Dinh nghia kieu ham sign (Doi dau)
func sign(a: Double) -> Double {
    return -a
}
func plus(a: Double, b: Double) -> Double {
    return a + b
}
func minus(a: Double, b: Double) -> Double {
    return a - b
}
func multi(a: Double, b: Double) -> Double {
    return a * b
}
func div(a: Double, b: Double) -> Double {
    return a / b
}

class CalculatorModel {
    //Khoi tao tham so tam thoi
    private var accumulator: Double?
    //Set toan hang
    func setOperand(operand: Double) {
        accumulator = operand
    }
    //Khai bao enum
    enum Operators {
        case constant(Double)
        //Bien ham kieu Double
        case unaryOperator((Double) -> Double)
        case binaryOperator((Double, Double) -> Double)
        case equal
    }
    //Dinh nghia dictionary
    let Operator: [String: Operators] = [
        "∏": Operators.constant(Double.pi),
        "e": Operators.constant(M_E),
        "√": Operators.unaryOperator(sqrt),
        "sin": Operators.unaryOperator(sin),
        "±": Operators.unaryOperator(sign),
        "+": Operators.binaryOperator(plus),
        "-": Operators.binaryOperator(minus),
        "x": Operators.binaryOperator(multi),
        ":": Operators.binaryOperator(div),
        "=": Operators.equal
    ]
    
    //Dinh nghia struct
    struct BinaryOperatorPending {
        var firstOperand: Double
        var binaryOperator: (Double, Double) -> Double
        func calculate(sercondOperad: Double) -> Double {
            return binaryOperator(firstOperand, sercondOperad)
        }
    }
    var temp: BinaryOperatorPending?

    //Ham tinh toan
    func calculate(mathOperator: String) {
        if let math = Operator[mathOperator] {
            switch math {
            case .constant(let value):
                accumulator = value
                
            case .unaryOperator(let unaryOperator):
                if let value = accumulator {
                    accumulator = unaryOperator(value)
                }
            case .binaryOperator(let binaryOperator):
                if accumulator != nil && temp != nil {
                    accumulator = temp!.calculate(sercondOperad: accumulator!)
                    temp=nil
                }
                if let value = accumulator {
                    temp = BinaryOperatorPending(firstOperand: value, binaryOperator: binaryOperator)
                }
            case .equal:
                if accumulator != nil && temp != nil {
                    accumulator = temp!.calculate(sercondOperad: accumulator!)
                    temp=nil
                }
            }
            
        }
    }
    //KHai bao bien tra ve -> result phai la optional
    var result: Double?{
        get {
            if let value = accumulator {
                return value
            }
            else
            {
                return 0
            }
        }
    }
}
