//
//  FoodManagerTests.swift
//  FoodManagerTests
//
//  Created by CNTT on 4/16/21.
//  Copyright © 2021 fit.tdc. All rights reserved.
//

import XCTest
@testable import FoodManager

class FoodManagerTests: XCTestCase {
    func testMealCreateSucceed() {
        //when meal name is not emtpy
        let notEmtpyMealName=Meal(mealName:"Mon an",mealImage:nil,mealRatingValue:4)
        XCAssertNotNil(notEmtpyMealName)
        //when rating value is 0
        let zeroRatingValue=Meal(mealName:"Mon an",mealImage:nil,mealRatingValue:0)
        XCAssertNotNil(zeroRatingValue)
        //when rating value is max
        let maxRatingValue=Meal(mealName:"Mon an",mealImage:nil,mealRatingValue:5)
        XCAssertNotNil(maxRatingValue)
    }
    func testMealCreateFail(){
        //when the meal name is emtpy
        let emtpyName=Meal(mealName:"",mealImage:nil,mealRatingValue:4)
        XCAssertNil(emtpyName)
        //when rating value is naeavite
        let negativeRatingValue=Meal(mealName:"Mon an",mealImage:nil,mealRatingValue:-1)
        XCAssertNil(negativeRatingValue)
        //when rating value is over
        let overRatingValue=Meal(mealName:"Mon an",mealImage:nil,mealRatingValue:6)
        XCAssertNil(overRatingValue)
   }

}
