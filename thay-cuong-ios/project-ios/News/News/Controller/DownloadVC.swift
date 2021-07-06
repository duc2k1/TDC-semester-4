//
//  DownloadArticle.swift
//  News
//
//  Created by Ho Viet Long on 5/6/21.
//

import CoreData
import SVProgressHUD //hien thi HUD (heads up display)

class DownloadVC: UIViewController, UITableViewDelegate,UITableViewDataSource {

    @IBOutlet weak var DownloadList: UITableView!
    //Properties
    var savedArticles = [ArticleDL]()
    var url: String!
    var titleArt:String!
    var contentArt:String!
    
    override func viewDidLoad() {
        super.viewDidLoad()
        DownloadList.delegate = self
        DownloadList.dataSource = self
        // Do any additional setup after loading the view.
    }
    override func viewDidAppear(_ animated: Bool) {
         getData()
     }
     
     
     func tableView(_ tableView: UITableView, titleForHeaderInSection section: Int) -> String? {
         return "Downloaded"
     }
     
     func tableView(_ tableView: UITableView, numberOfRowsInSection section: Int) -> Int {
         return savedArticles.count
     }
     
     func tableView(_ tableView: UITableView, cellForRowAt indexPath: IndexPath) -> UITableViewCell {
         if let cell = tableView.dequeueReusableCell(withIdentifier: "longkunz")
         {
            cell.textLabel?.text = savedArticles[indexPath.row].title!
            titleArt=savedArticles[indexPath.row].title!
            return cell
         }
         
         return UITableViewCell()
     }
     
    
     func tableView(_ tableView: UITableView, didSelectRowAt indexPath: IndexPath) {
         
         url = savedArticles[indexPath.row].url!
        contentArt=savedArticles[indexPath.row].content!
        titleArt = savedArticles[indexPath.row].title!
         performSegue(withIdentifier: "dllist", sender: self)
        print("Selected")
     }
     //MARK: chia se du lieu sang cho DownloadedVC
     override func prepare(for segue: UIStoryboardSegue, sender: Any?) {
         if let des = segue.destination as? BrowserDownloadVC
         {
            des.articleTitle = titleArt!
            des.articleContent = contentArt!
         }
     }
     //MARK: hien thi table view
     
     func tableView(_ tableView: UITableView, editActionsForRowAt indexPath: IndexPath) -> [UITableViewRowAction]? {
         let deleteAction = UITableViewRowAction(style: .destructive, title: "Delete") { (UITableViewRowAction, IndexPath) in
          
             //Goi ham xoa du lieu
             self.deleteData(i: indexPath.row)
            //Cap nhat lai du lieu moi
             self.getData()
             
             self.DownloadList.reloadData()
             
         }
        let shareAction = UITableViewRowAction(style: .normal, title: "Share") { (UITableViewRowAction, IndexPath) in
            self.sociaMediaShare(url: self.savedArticles[IndexPath.row].url ?? "No url found")
            
            
        }
        
        shareAction.backgroundColor = #colorLiteral(red: 0.9529411793, green: 0.6862745285, blue: 0.1333333403, alpha: 1)
        return [deleteAction,shareAction]
         
     }
    
     //MARK: chuc nang xoa du lieu
     func deleteData(i: Int)
     {
         guard let mc = AppDel?.persistentContainer.viewContext else {return }
         //Ham delete de xoa du lieu
         mc.delete(savedArticles[i])
         do
         {
            //Luu lai du lieu moi
             try mc.save()
         }
         catch
         {
             print(error.localizedDescription)
         }
         
     }
     
     //MARK: Lay du lieu tu database va gan vao uitableview
     func getData()
     {
         let fetchrequest = NSFetchRequest<ArticleDL>(entityName: "ArticleDL")
         
         do
         {
             guard  let mc = AppDel?.persistentContainer.viewContext else {return }
             savedArticles = try mc.fetch(fetchrequest)
             self.DownloadList.reloadData()
         }
         catch
         {
             print(error.localizedDescription)
         }
     }
     
     
    //MARK: Chuc nang chia se
     func sociaMediaShare(url: String)
     {
         let vc = UIActivityViewController(activityItems: [url], applicationActivities: [])
         
         
         present(vc, animated: true, completion: nil)
         
     }

}
