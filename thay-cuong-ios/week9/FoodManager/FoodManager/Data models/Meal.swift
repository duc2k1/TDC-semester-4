import UIKit

class Meal{
  var mealName:String
  var mealImage:UIImage?
  var mealRatingValue:Int
  //constructor
  init?(mealName:String,mealImage:UIImage?,mealRatingValue:Int){
    if mealName.isEmpty {
      return nil
    }
    if mealRatingValue<0 || mealRatingValue>5 {
      return nil
    }
    self.mealName=mealName
    self.mealImage=mealImage
    self.mealRatingValue=mealRatingValue
  }
}