param(
    [string]$ModrinthRoot = "$env:APPDATA/ModrinthApp",
    [string]$ProfileName = 'Remarkably Optimized'
)
$ErrorActionPreference = 'Stop'
$taskProfile = Join-Path $ModrinthRoot "profiles/$ProfileName"
$taskMinecraft = Join-Path $taskProfile '.fabric/remappedJars/minecraft-1.21.11-0.18.6/client-intermediary.jar'
if (!(Test-Path -LiteralPath $taskMinecraft)) { throw 'Launch the Minecraft 1.21.11 / Fabric 0.18.6 profile once to populate its remapped cache.' }
$taskConfig = Join-Path $taskProfile 'mods/fzzy_config-0.7.6+1.21.11.jar'
$taskClasspath = (@($taskMinecraft, $taskConfig) +
    @(Get-ChildItem (Join-Path $taskProfile '.fabric/processedMods') -Filter '*.jar' | ForEach-Object FullName) +
    @(Get-ChildItem (Join-Path $ModrinthRoot 'meta/libraries') -Filter '*.jar' -Recurse | ForEach-Object FullName)) -join ';'
$taskBuild = Join-Path $PSScriptRoot 'build'
$taskClasses = Join-Path $taskBuild ([guid]::NewGuid().ToString('N'))
New-Item -ItemType Directory -Force $taskClasses | Out-Null
$taskArgs = @('--release', '21', '-proc:none', '-classpath', ('"' + $taskClasspath.Replace('\','/') + '"'), '-d', ('"' + $taskClasses.Replace('\','/') + '"')) +
    @(Get-ChildItem (Join-Path $PSScriptRoot 'src/main/java') -Filter '*.java' -Recurse | ForEach-Object { '"' + $_.FullName.Replace('\','/') + '"' })
$taskArgFile = Join-Path $taskBuild 'javac.args'
$taskArgs | Set-Content -LiteralPath $taskArgFile -Encoding utf8
& javac "@$taskArgFile"
if ($LASTEXITCODE -ne 0) { throw 'Compilation failed' }
$taskJar = Join-Path $taskBuild 'Spear-Dashing-1.1.0-optimized.jar'
& jar --create --file $taskJar -C $taskClasses . -C (Join-Path $PSScriptRoot 'src/main/resources') .
if ($LASTEXITCODE -ne 0) { throw 'Packaging failed' }
Write-Output $taskJar
