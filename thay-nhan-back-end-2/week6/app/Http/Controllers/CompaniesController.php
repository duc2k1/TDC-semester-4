<?php

namespace App\Http\Controllers;

use Illuminate\Foundation\Auth\Access\AuthorizesRequests;
use Illuminate\Foundation\Bus\DispatchesJobs;
use Illuminate\Foundation\Validation\ValidatesRequests;
use Illuminate\Routing\Controller as BaseController;
use App\Models\Companies;
class CompaniesController extends Controller
{
    public function getPagination(){
        $obj=new Companies();
        $companies=$obj->paginate(15);
        return view('companies',['companies'=>$companies]);
    }
}
