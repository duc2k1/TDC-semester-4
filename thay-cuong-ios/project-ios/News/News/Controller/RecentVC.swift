//
//  ViewController.swift
//  News
//
//  Created by Ho Viet Long on 5/6/21.
//

import CoreData //luu tru du lieu tren dien thoai
import SVProgressHUD //hien thi HUD (heads up display)
import Network //tao ket noi mang de gui va nhan du lieu
import Firebase

let AppDel = UIApplication.shared.delegate as? AppDelegate

class RecentVC: UIViewController,UITableViewDelegate,UITableViewDataSource{
   //Properties
    var monitor = NWPathMonitor()
    var articles = [Articles]()
    var newsurl: String!
    var newstitle: String!
    let d = DArticle()
    let database=Database.database().reference()
    let defaultValues:[String:Int]=[
        "heathViews":0,
        "scienceViews":0,
        "entertainmentViews":0,
        "technologyViews":0,
    ]
    //get id iphone
    let idIphone=UIDevice.current.identifierForVendor?.uuidString
    @IBOutlet weak var navigationbar: UINavigationItem!
    @IBOutlet weak var headlinestableview: UITableView!
    //
    //khi view controller da duoc nap vao bo nho -> viewDidLoad duoc goi va chi duoc goi 1 lan
    override func viewDidLoad() {
        super.viewDidLoad()
        //set dafault value if id iphone is not exists
        database.child("views/\(idIphone!)").getData{ [self]  (error, snapshot) in
            if let error = error {
                print("Error getting data \(error)")
            }
            else if !snapshot.exists() {
                print("No data available")
                //set default value by id phone
                self.database.child("views/\(self.idIphone!)").setValue(self.defaultValues)
            }
        }
        //Show error message khi khong co internet
        monitor.pathUpdateHandler = { path in
            
            if path.status == .unsatisfied
            {
                SVProgressHUD.showError(withStatus: "No internet connection")
                return
            }
        }
        
        let queue = DispatchQueue(label: "Monitor")
        monitor.start(queue: queue)
        //Lay tin tuc
        if headlinestableview.visibleCells.isEmpty
        {
            SVProgressHUD.show(withStatus: "Getting headlines")
            SVProgressHUD.setBorderColor(#colorLiteral(red: 0, green: 0, blue: 0, alpha: 1))
            SVProgressHUD.setBorderWidth(2)
            SVProgressHUD.setHapticsEnabled(true)
            SVProgressHUD.setRingThickness(4)
            
        }
        title = "Headlines"
        self.headlinestableview.delegate = self
        self.headlinestableview.dataSource = self
        NetworkService.sharedobj.getHeadLines { (a) in
            self.articles = a
            self.headlinestableview.reloadData()
            SVProgressHUD.dismiss()
        }
    }
    //tra ve so news
    func tableView(_ tableView: UITableView, numberOfRowsInSection section: Int) -> Int {
        return articles.count
    }
    func tableView(_ tableView: UITableView, cellForRowAt indexPath: IndexPath) -> UITableViewCell {
       if let cell = tableView.dequeueReusableCell(withIdentifier: "cell") as? HeadLinesCellTableViewCell
        {
            
            let data = articles[indexPath.row]
            //Set default content khi khong lay duoc tu api
            cell.updateCell(title:data.title ?? "Not Found", body: data.content ?? "No Body", imgurl: data.urlToImage ?? "https://en.wikipedia.org/wiki/Pages_(word_processor)#/media/File:Pages_Icon.png")
            return cell
        }
        return UITableViewCell()
    }
    func tableView(_ tableView: UITableView, didSelectRowAt indexPath: IndexPath) {
        newsurl = articles[indexPath.row].url!
        newstitle = articles[indexPath.row].title!
        performSegue(withIdentifier: "segue", sender: self)
        
    }
    
    //chuyen du lieu sang BrowserVC
    override func prepare(for segue: UIStoryboardSegue, sender: Any?) {
        
        if let destinationVC = segue.destination as? BrowserRecentVC
        {
            destinationVC.url = newsurl
            destinationVC.newstitle = newstitle
        }
        
    }
    //Swipe action
    func tableView(_ tableView: UITableView, editActionsForRowAt indexPath: IndexPath) -> [UITableViewRowAction]? {
        let action = UITableViewRowAction(style: .normal, title: "Share") { (UITableViewRowAction, IndexPath) in
            
           
            self.socialMediaShare(index: indexPath.row)
            
            
        }
        
        action.backgroundColor = #colorLiteral(red: 0.5568627715, green: 0.3529411852, blue: 0.9686274529, alpha: 1)
        
        
        let save = UITableViewRowAction(style: .normal, title: "Bookmark") { (UITableViewRowAction, IndexPath) in
            self.saveData(i: IndexPath.row)
        }
        
        save.backgroundColor = #colorLiteral(red: 0.9529411793, green: 0.6862745285, blue: 0.1333333403, alpha: 1)
       
        let download = UITableViewRowAction(style: .normal, title: "Download") { (UITableViewRowAction, IndexPath) in
            self.downloadData(i: IndexPath.row)
        }
        download.backgroundColor = #colorLiteral(red: 0.2588235438, green: 0.7568627596, blue: 0.9686274529, alpha: 1)
        
        return [action, save, download]
    }
    
    
    //Lay noi dung html
    func getContentFromURL(){
        if let urlDL = URL(string: newsurl!){
            do {
                let contents = try String(contentsOf: urlDL)
            } catch {
                // contents could not be loaded
                SVProgressHUD.showError(withStatus: "URL could not be loaded")
            }
        }
    }
    //Ham xu ly luu bai viet vao danh sach bookmark
    func saveData(i: Int)
    {
        //Lay context
        guard let managedContext = AppDel?.persistentContainer.viewContext else {return }
        //Lay coredate
        let articetobesave = DArticle(context: managedContext)
        //Gan du lieu cho coredate
        articetobesave.title = articles[i].title!
        articetobesave.articleurl = articles[i].url!
        
        do{
            //Xu ly luu du lieu
          try  managedContext.save()
            //Thong bao khi luu thanh cong
            let ac = UIAlertController(title: "Info", message: "Article added to Bookmark", preferredStyle: .alert)
            ac.addAction(UIAlertAction(title: "Ok", style: .default, handler: nil))
            
            present(ac, animated: true, completion: nil)
            
            
        }
        
        catch{
            print(error.localizedDescription)
        }
        print("Added to bookmark")
        
        
    }
    
    //MARK: Download content function
    func downloadData(i: Int)
    {
        //Lay context
        guard let managedContext = AppDel?.persistentContainer.viewContext else {return }
        // lấy Entity name
        let articetobesave = ArticleDL(context: managedContext)
        //Gan tieu de bai viet de luu
        articetobesave.title = articles[i].title!
        if let urlDL = URL(string: articles[i].url!){
            do {
                //Gan url bai viet de luu
                articetobesave.url = articles[i].url!
                let contents = try String(contentsOf: urlDL)
                //Gan noi dung bai viet
                articetobesave.content=contents
 
            } catch {
                // Hien thi loi: contents could not be loaded
                SVProgressHUD.showError(withStatus: "URL could not be loaded")
            }
        }
        do{
            //Luu du lieu
          try  managedContext.save()
            //Thong bao khi luu du lieu
            let ac = UIAlertController(title: "Notification", message: "Article added to Downloaded", preferredStyle: .alert)
            ac.addAction(UIAlertAction(title: "Ok", style: .default, handler: nil))
            
            present(ac, animated: true, completion: nil)
        }
        catch{
            print(error.localizedDescription)
        }
        print("Added to download")
    }
    //Chuc nang chia se bai viet
    func socialMediaShare(index: Int)
    {
        let vc = UIActivityViewController(activityItems: [articles[index].url ?? "Not found"], applicationActivities: [])
        present(vc, animated: true, completion: nil)
    }
    //Nut refresh
    @IBAction func refreshbtn(_ sender: Any) {
        
        
        NetworkService.sharedobj.getHeadLines { (a) in
            
            SVProgressHUD.show(withStatus: "Refreshing...")
            self.articles = a
            self.headlinestableview.reloadData()
            SVProgressHUD.dismiss(withDelay: 2)
        }
        
    }
}

