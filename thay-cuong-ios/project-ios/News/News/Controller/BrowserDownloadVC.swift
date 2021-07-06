//
//  DownloadedVC.swift
//  News
//
//  Created by Ho Viet Long on 5/6/21.
//

import WebKit //hien thi trang web dua vao du lieu dc truyen vao (chuoi html, url)

class BrowserDownloadVC: UIViewController,  WKUIDelegate, WKNavigationDelegate {
    //Properties
    //lay du lieu tu DownloadArticle
    var articleContent: String!
    var articleTitle: String!
    @IBOutlet weak var lbTitle: UILabel!
    //
    @IBAction func btnExit(_ sender: Any) {
        dismiss(animated: true, completion: nil)
    }
    @IBOutlet weak var webkitView: WKWebView!
    override func viewDidLoad() {
        super.viewDidLoad()
        //Set title cho label
        lbTitle.text = articleTitle
        webkitView.loadHTMLString(articleContent, baseURL: nil)
    }
}
