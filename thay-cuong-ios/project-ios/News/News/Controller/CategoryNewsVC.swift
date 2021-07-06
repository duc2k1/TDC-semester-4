//
//  CategoryNewsVC.swift
//News
//
//  Created by Ho Viet Long on 5/6/21.
//

import Network //tao ket noi mang de gui va nhan du lieu
import SVProgressHUD //hien thi HUD (heads up display)
import Firebase

class CategoryNewsVC: UIViewController,UITableViewDelegate,UITableViewDataSource {
    //Properties
    var articles = [Articles]()
    var newsurl: String?
    var ntitle: String?
    let monitor = NWPathMonitor()
    var i:Int!
    @IBOutlet weak var tv: UITableView!
    let database=Database.database().reference()
    //get id iphone
    let idIphone=UIDevice.current.identifierForVendor?.uuidString
    //
    //Xu ly dem so lan truy cap category
    func getAndSetViews(category: String?){
        database.child("views/\(idIphone!)").getData{ [self] (error, snapshot) in
            if let error = error {
                print("Error getting data \(error)")
            }
            else if snapshot.exists() {
                let value=snapshot.value as! [String: Int]
                var views=value[category!]!
                //increase views
                views+=1
                self.database.child("views/\(self.idIphone!)/\(category!)").setValue(views)
            }
        }
    }
    override func viewDidLoad() {
        super.viewDidLoad()
        monitor.pathUpdateHandler = { path in
            
            if path.status == .unsatisfied
            {
                SVProgressHUD.showError(withStatus: "No internet connection")
                return
            }
        }
        
        let queue = DispatchQueue(label: "Monitor")
        monitor.start(queue: queue)
        //Xu ly show du lieu tuong ung theo category
        if i == 0
        {
           //MARK:firebase heath
            getAndSetViews(category: "heathViews")
            //
            title = "Health News"
            
            if tv.visibleCells.isEmpty
            {
                SVProgressHUD.show(withStatus: "Getting news...")
                SVProgressHUD.setBorderColor(#colorLiteral(red: 0, green: 0, blue: 0, alpha: 1))
                SVProgressHUD.setBorderWidth(2)
                SVProgressHUD.setHapticsEnabled(true)
                SVProgressHUD.setRingThickness(4)
            }
            tv.delegate = self
                         tv.dataSource = self
            NetworkService.sharedobj.getHealthNews { (articles) in
               
                self.articles = articles
                print(self.articles.count)
                self.tv.reloadData()
                
                SVProgressHUD.dismiss()
              
            }
        }
        
        else if i == 3
        {
            //MARK:firebase technology
             getAndSetViews(category: "technologyViews")
             //
           title = "Technology News"
            
            if tv.visibleCells.isEmpty
            {
                SVProgressHUD.show(withStatus: "Getting news...")
                SVProgressHUD.setBorderColor(#colorLiteral(red: 0, green: 0, blue: 0, alpha: 1))
                SVProgressHUD.setBorderWidth(2)
                SVProgressHUD.setHapticsEnabled(true)
                SVProgressHUD.setRingThickness(4)
            }
            
            tv.delegate = self
            tv.dataSource = self
            
            NetworkService.sharedobj.getTechNews { (a) in
                self.articles = a
                print(a.count)
                DispatchQueue.main.async {
                    self.tv.reloadData()
                }
                SVProgressHUD.dismiss()
            }
        }
        else if i == 2
        {
            //MARK:firebase entertainment
             getAndSetViews(category: "entertainmentViews")
             //
            title = "Entertaiment News"
            if tv.visibleCells.isEmpty
            {
                SVProgressHUD.show(withStatus: "Getting news...")
                SVProgressHUD.setBorderColor(#colorLiteral(red: 0, green: 0, blue: 0, alpha: 1))
                SVProgressHUD.setBorderWidth(2)
                SVProgressHUD.setHapticsEnabled(true)
                SVProgressHUD.setRingThickness(4)
            }
            NetworkService.sharedobj.getEntertainmentNews { (a) in
                self.articles = a
                DispatchQueue.main.async {
                    self.tv.reloadData()
                }
                SVProgressHUD.dismiss()
            }
        }
        else if i == 1
        {
            //MARK:firebase science
             getAndSetViews(category: "scienceViews")
             //
            title = "Science News"
            if tv.visibleCells.isEmpty
            {
                SVProgressHUD.show(withStatus: "Getting news...")
                SVProgressHUD.setBorderColor(#colorLiteral(red: 0, green: 0, blue: 0, alpha: 1))
                SVProgressHUD.setBorderWidth(2)
                SVProgressHUD.setHapticsEnabled(true)
                SVProgressHUD.setRingThickness(4)
            }
            NetworkService.sharedobj.getScienceNews { (a) in
                self.articles = a
                DispatchQueue.main.async {
                    self.tv.reloadData()
                }
                
                SVProgressHUD.dismiss()
            }
        }
    }
    func tableView(_ tableView: UITableView, numberOfRowsInSection section: Int) -> Int {
        return articles.count
    }
    //Xu ly khi khong lay duoc du lieu
    func tableView(_ tableView: UITableView, cellForRowAt indexPath: IndexPath) -> UITableViewCell {
        if let cell = tableView.dequeueReusableCell(withIdentifier: "cellnews") as? HeadLinesCellTableViewCell
        {
            cell.updateCell(title: articles[indexPath.row].title ?? "No title", body: articles[indexPath.row].content ?? "No body", imgurl: articles[indexPath.row].urlToImage ?? "https://en.wikipedia.org/wiki/Pages_(word_processor)#/media/File:Pages_Icon.png")
            
            return cell
        }
        
        return UITableViewCell()
        
    }
    
    func tableView(_ tableView: UITableView, didSelectRowAt indexPath: IndexPath) {
        if let nurl = articles[indexPath.row].url
        {
            self.newsurl = nurl
        }
        
        if let ntit = articles[indexPath.row].title
        {
            self.ntitle = ntit
        }
        
        performSegue(withIdentifier: "bsegue", sender: self)
    }
    //Swipe action
    func tableView(_ tableView: UITableView, editActionsForRowAt indexPath: IndexPath) -> [UITableViewRowAction]? {
        //Share
        let action = UITableViewRowAction(style: .normal, title: "Share") { (UITableViewRowAction, IndexPath) in
            
           
            self.socialMediaShare(index: indexPath.row)
            
            
        }
        
        action.backgroundColor = #colorLiteral(red: 0.5568627715, green: 0.3529411852, blue: 0.9686274529, alpha: 1)
        //Bookmark
        let saveAction = UITableViewRowAction(style: .default, title: "Bookmark") { (UITableViewRowAction, IndexPath) in
            self.saveData(i: indexPath.row)
        }
        //Download
        let download = UITableViewRowAction(style: .normal, title: "Download") { (UITableViewRowAction, IndexPath) in
            self.downloadData(i: IndexPath.row)
        }
        download.backgroundColor = #colorLiteral(red: 0.2588235438, green: 0.7568627596, blue: 0.9686274529, alpha: 1)
        saveAction.backgroundColor = #colorLiteral(red: 0.9529411793, green: 0.6862745285, blue: 0.1333333403, alpha: 1)
        return [action,saveAction,download]
    }
    //Chuc nang luu bai viet vao bookmark
    func saveData(i: Int)
    {
        guard let mc = AppDel?.persistentContainer.viewContext else {return}
        let a = DArticle(context: mc)
        a.title = articles[i].title!
        a.articleurl = articles[i].url!
        do
        {
            try mc.save()
            let ac = UIAlertController(title: "Info", message: "Article added to Bookmark", preferredStyle: .alert)
            ac.addAction(UIAlertAction(title: "Ok", style: .default, handler: nil))
            
            present(ac, animated: true, completion: nil)
           
        }
        
        catch
        {
            print(error.localizedDescription)
        }
    }
    //Chuc nang chia se bai viet
    func socialMediaShare(index: Int)
    {
        let vc = UIActivityViewController(activityItems: [articles[index].url ?? "Not found"], applicationActivities: [])
        present(vc, animated: true, completion: nil)
    }
    //MARK: Download content function
    func downloadData(i: Int)
    {
        guard let managedContext = AppDel?.persistentContainer.viewContext else {return }
        // lấy Entity name
        let articetobesave = ArticleDL(context: managedContext)
        
        articetobesave.title = articles[i].title!
        //Lay source code html tu url
        if let urlDL = URL(string: articles[i].url!){
            do {
                //Luu xuong coredata
                articetobesave.url = articles[i].url!
                let contents = try String(contentsOf: urlDL)
                
                articetobesave.content=contents
            } catch {
                // contents could not be loaded
                SVProgressHUD.showError(withStatus: "URL could not be loaded")
            }
        }
        
        //articetobesave.articleurl = articles[i].url!
        
        do{
          try  managedContext.save()
            
            let ac = UIAlertController(title: "Notification", message: "Article added to Downloaded", preferredStyle: .alert)
            ac.addAction(UIAlertAction(title: "Ok", style: .default, handler: nil))
            present(ac, animated: true, completion: nil)
        }
        catch{
            print(error.localizedDescription)
        }
        print("Added to download")
        
    }
    //Truyen tam so sang cho man hinh hien thi chi tiet
    override func prepare(for segue: UIStoryboardSegue, sender: Any?) {
        if let destinationVC = segue.destination as? BrowserCategoryNewsVC
        {
            destinationVC.url = self.newsurl
            destinationVC.ntitle = self.ntitle
        }
    }
}
