<?php

namespace Database\Seeders;

use Illuminate\Database\Seeder;
use Illuminate\Support\Facades\DB;
use Illuminate\Support\Facades\Hash;
use Illuminate\Support\Str;
class TrainerSeeder extends Seeder
{
    /**
     * Run the database seeds.
     *
     * @return void
     */
    public function run()
    {
        for($i=1;$i<=5000000;$i++){
			DB::table('trainers')->insert([
            'trainer_name' => Str::random(10),
            'company_id' => rand(1,100),
            'trainer_email' => Str::random(10).'@gmail.com',
			'trainer_phone' => '0'.mt_rand(1000000,9999999),
        ]);
		}
    }
}
