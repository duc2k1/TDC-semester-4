//
//  BrowserVCsecond.swift
//News
//
//  Created by Ho Viet Long on 5/6/21.
//

import SVProgressHUD //hien thi HUD (heads up display)
import KSBGradientView //tao hieu ung mau nen cho UIView
import WebKit //hien thi trang web dua vao du lieu dc truyen vao (chuoi html, url)
class BrowserCategoryNewsVC: UIViewController, WKNavigationDelegate {
    //Properties
    //url, ntitle duoc truyen tu CategoryNewsVC
    var url: String?
    var ntitle: String!
    @IBOutlet weak var webviewnews: WKWebView!
    @IBOutlet weak var titlelbl: UILabel!
    @IBOutlet weak var titleview: UIView!
    //
    override func viewDidLoad() {
        super.viewDidLoad()
        //Set mau cho tieu de dung KSBGradientView
        titleview.applyHorizontalGradient(startcolor: UIColor.purple, endcolor: UIColor.blue)
       if let u = url
       {
        webviewnews.load(URLRequest(url: URL(string:u)!))
       }
       else
       {
        let ac = UIAlertController(title: "Error", message: "URL Not found", preferredStyle: .alert)
        ac.addAction(UIAlertAction(title: "Ok", style: .default, handler: nil))
        present(ac, animated: true, completion: nil)
       }
       if let titlenews = ntitle
       {
        titlelbl.text = titlenews
       }
       else
       {
        titlelbl.text = ""
       }
    }
   //Nut thoat
    @IBAction func closebtnclicked(_ sender: Any) {
        dismiss(animated: true, completion: nil)
    }
}
