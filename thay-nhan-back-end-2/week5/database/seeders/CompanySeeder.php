<?php

namespace Database\Seeders;

use Illuminate\Database\Seeder;
use Illuminate\Support\Facades\DB;
use Illuminate\Support\Facades\Hash;
use Illuminate\Support\Str;
class CompanySeeder extends Seeder
{	
    /**
     * Run the database seeds.
     *
     * @return void
     */
    public function run()
    {
        for($i=1;$i<=5000000;$i++){
			$min_epoch = strtotime('1999-1-1 00:00:00');
			$max_epoch = strtotime('2021-1-1 00:00:00');
			$rand_epoch = rand($min_epoch, $max_epoch);
			$rdDate=date('Y-m-d H:i:s', $rand_epoch);
			DB::table('companies')->insert([
            'company_name' => Str::random(10),
            'company_web' => Str::random(10).'.com',
            'company_address' => Str::random(10),
			'company_code' => Str::random(10),
			'company_phone' => '0'.mt_rand(1000000,9999999),
			'created_at' => $rdDate,
			'updated_at' => $rdDate,
        ]);
		}
    }
}
