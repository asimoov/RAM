# encoding: UTF-8
# This file is auto-generated from the current state of the database. Instead
# of editing this file, please use the migrations feature of Active Record to
# incrementally modify your database, and then regenerate this schema definition.
#
# Note that this schema.rb definition is the authoritative source for your
# database schema. If you need to create the application database on another
# system, you should be using db:schema:load, not running all the migrations
# from scratch. The latter is a flawed and unsustainable approach (the more migrations
# you'll amass, the slower it'll run and the greater likelihood for issues).
#
# It's strongly recommended that you check this file into your version control system.

ActiveRecord::Schema.define(version: 20131218140804) do

  create_table "causes", force: true do |t|
    t.string   "disease"
    t.integer  "cid_id"
    t.integer  "research_id"
    t.datetime "created_at"
    t.datetime "updated_at"
  end

  add_index "causes", ["cid_id"], name: "index_causes_on_cid_id"
  add_index "causes", ["research_id"], name: "index_causes_on_research_id"

  create_table "cids", force: true do |t|
    t.string   "code"
    t.string   "description"
    t.datetime "created_at"
    t.datetime "updated_at"
  end

  create_table "hospitals", force: true do |t|
    t.string   "name"
    t.string   "acronym"
    t.string   "country"
    t.datetime "created_at"
    t.datetime "updated_at"
  end

  create_table "hospitals_users", force: true do |t|
    t.integer "user_id"
    t.integer "hospital_id"
  end

  add_index "hospitals_users", ["hospital_id"], name: "index_hospitals_users_on_hospital_id"
  add_index "hospitals_users", ["user_id"], name: "index_hospitals_users_on_user_id"

  create_table "medications", force: true do |t|
    t.string   "name"
    t.string   "way"
    t.string   "dose"
    t.string   "indication"
    t.date     "start"
    t.date     "end"
    t.datetime "created_at"
    t.datetime "updated_at"
  end

  create_table "researches", force: true do |t|
    t.string   "handbook"
    t.string   "name"
    t.string   "cns"
    t.string   "sex"
    t.float    "weight"
    t.float    "height"
    t.string   "color"
    t.string   "unit"
    t.integer  "bed"
    t.date     "admission"
    t.integer  "hospital_id"
    t.integer  "cause_id"
    t.datetime "created_at"
    t.datetime "updated_at"
  end

  add_index "researches", ["cause_id"], name: "index_researches_on_cause_id"
  add_index "researches", ["hospital_id"], name: "index_researches_on_hospital_id"

  create_table "users", force: true do |t|
    t.string   "name",                   default: "",    null: false
    t.boolean  "admin",                  default: false, null: false
    t.string   "email",                  default: "",    null: false
    t.string   "encrypted_password",     default: "",    null: false
    t.string   "reset_password_token"
    t.datetime "reset_password_sent_at"
    t.datetime "remember_created_at"
    t.integer  "sign_in_count",          default: 0,     null: false
    t.datetime "current_sign_in_at"
    t.datetime "last_sign_in_at"
    t.string   "current_sign_in_ip"
    t.string   "last_sign_in_ip"
    t.datetime "created_at"
    t.datetime "updated_at"
  end

  add_index "users", ["email"], name: "index_users_on_email", unique: true
  add_index "users", ["reset_password_token"], name: "index_users_on_reset_password_token", unique: true

end
