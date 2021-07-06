//
//  BrowserVC.swift
//  News
//
//  Created by Ho Viet Long on 5/6/21.
//

import KSBGradientView //tao hieu ung mau nen cho UIView
import WebKit //hien thi trang web dua vao du lieu dc truyen vao (chuoi html, url)

class BrowserRecentVC: UIViewController {
    @IBOutlet weak var titlenewslbl: UILabel!
    //lay du lieu tu ViewController
    var url: String?
    var newstitle: String?
    @IBOutlet weak var webView: WKWebView!
    @IBOutlet weak var closebtn: UIButton!
    @IBOutlet weak var titlebarview: UIView!
    //
    override func viewDidLoad() {
        super.viewDidLoad()
        titlebarview.applyHorizontalGradient(startcolor: UIColor.purple, endcolor: UIColor.systemPink)
      if let titles = newstitle
      {
        titlenewslbl.text = titles
      }
        
      if let newsurl = url
      {
        webView.load(URLRequest(url: URL(string: newsurl)!))
      }
    }
    @IBAction func closebtn(_ sender: UIButton) {
        
          dismiss(animated: true, completion: nil)
    }
}
