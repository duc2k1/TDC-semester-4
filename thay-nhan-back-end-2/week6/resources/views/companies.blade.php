<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Document</title>
    <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css" integrity="sha384-ggOyR0iXCbMQv3Xipma34MD+dH/1fQ784/j6cY/iJTQUOhcWr7x9JvoRxT2MZw1T" crossorigin="anonymous">
</head>
<body>
    <table class="table">
  <thead>
    <tr>
      <th scope="col">Id</th>
      <th scope="col">Company name</th>
      <th scope="col">Company web</th>
      <th scope="col">Company address</th>
      <th scope="col">Company code</th>
      <th scope="col">Company phone</th>
      <th scope="col">Created at</th>
      <th scope="col">Updated at</th>
    </tr>
  </thead>
  <tbody>
  @foreach ($companies as $company):
            <tr>
            <th scope='row'>{!! $company->company_id !!}</td>
                <td>{!! $company->company_name !!}</td>
                <td>{!! $company->company_web !!}</td>
                <td>{!! $company->company_address !!}</td>
                <td>{!! $company->company_code !!}</td>
                <td>{!! $company->company_phone !!}</td>
                <td>{!! $company->created_at !!}</td>
                <td>{!! $company->updated_at !!}</td>
            </tr>
            @endforeach
  </tbody>
</table>
{{!! $companies->links() !!}}
</body>
</html>